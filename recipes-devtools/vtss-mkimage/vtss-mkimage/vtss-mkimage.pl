#!/usr/bin/env perl

use warnings;
use strict;
use Data::Dumper;
use Getopt::Long;
use String::CRC::Cksum qw(cksum);

my ($machine, $soc, $family);
my ($k_name, $r_name, $m_name, $o_name);
my $verbose;

GetOptions ("machine=s"  => \$machine,
            "soc=s"      => \$soc,
            "socfam=i"   => \$family,
            "kernel=s"   => \$k_name,
            "rootfs=s"   => \$r_name,
            "metadata=s" => \$m_name,
            "out=s"      => \$o_name,
            "verbose"    => \$verbose)
    or die("Error in command line arguments\n");

my ($k_data, $r_data, $m_data) = ("", "", "");

die("$k_name: $!") unless(-f $k_name);

$k_data = slurp($k_name);

if(defined($r_name)) {
    $r_data = slurp($r_name);
}

if(defined($m_name)) {
    $m_data = slurp($m_name);
}

if(defined($o_name)) {
    open (STDOUT, '>:raw', $o_name) || die("$o_name: $!");
}

my ($hdrlen) = 1024;        # For starters, can be extended
my ($k_offset) = $hdrlen;
my ($r_offset) = $k_offset + pad(length($k_data));
my ($m_offset) = $r_offset + pad(length($r_data));
my ($totlen)   = $hdrlen + pad(length($k_data)) + pad(length($r_data)) + length($m_data);

my ($header) = pack("V2 V3 Z32Z32V1 V3",

                    # 2 4-byte magics
                    0xf7431973, # Magic cookie1
                    0x8932ab19, # Magic cookie2

                    # Header length, crc and total image length
                    $hdrlen,    # Header lenghth
                    0,          # Placeholder header CRC - offset 12
                    $totlen,    # Total length

                    # machine (target) name, soc-name, soc-family ordinal (integer)
                    $machine, $soc, $family,

                    # length, offset and crc for: Kernel, Rootfs (optional), metadata file (optional)
                    length($k_data), $k_offset, scalar(cksum($k_data)));

$header .= pack("V3", $r_data ? (length($r_data), $r_offset, scalar(cksum($r_data))) : (0, 0, 0));
$header .= pack("V3", $m_data ? (length($m_data), $m_offset, scalar(cksum($m_data))) : (0, 0, 0));

$header = nullpad($header);     # Padd to header/block size

substr($header, 12, 4) = pack("V", cksum($header)); # replace CRC

syswrite(STDOUT, $header);
padwrite(\*STDOUT, $k_data);
padwrite(\*STDOUT, $r_data);
padwrite(\*STDOUT, $m_data);

exit(0);

sub slurp
{
    my ($file) = @_;
    my($fsize) = -s $file;
    my($fdata);

    open(F, '<:raw', $file) || die("$file: $!");
    die "$file: $!" unless(sysread(F, $fdata, $fsize) == $fsize);
    close(F);

    return $fdata;
}

sub pad
{
    my($len) = @_;
    my($bs) = 1024;

    return ($len + $bs - 1) & ~($bs - 1);
}

sub nullpad
{
    my($buf) = @_;
    if($buf) {
        my ($len) = length($buf);
        my ($plen) = pad($len);
        $buf .= "\0" x ($plen - $len) if($plen);
    }
    $buf;
}

sub padwrite
{
    my($fh, $buf) = @_;
    if($buf) {
        $buf = nullpad($buf);
        syswrite($fh, $buf);
    }
}
