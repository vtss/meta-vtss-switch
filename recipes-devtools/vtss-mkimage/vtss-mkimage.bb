SUMMARY = "Utility for creating Vitesse Linux boot images"
SECTION = "devel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"

PV = "1.0"
PR = "r0"

SRC_URI = "file://vtss-mkimage.pl \
        file://String/CRC/Cksum.pm \
        file://COPYING"

inherit cpan-base perlnative

# No subdir
S = "${WORKDIR}"

export PERL_LIB = "${STAGING_LIBDIR}${PERL_OWN_DIR}/perl/${@get_perl_version(d)}"

do_compile() {
}

do_install() {
        echo "$PERL_LIB"
        install -d ${D}${sbindir}
        install -m 0755 vtss-mkimage.pl ${D}${sbindir}/
        install -d ${D}$PERL_LIB/String/CRC
        install -m 0644 String/CRC/Cksum.pm ${D}$PERL_LIB/String/CRC/
}

BBCLASSEXTEND = "native"
