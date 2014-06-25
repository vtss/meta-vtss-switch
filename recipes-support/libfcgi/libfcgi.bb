LICENSE = "OpenMarket"
# Or MIT?
DESCRIPTION = "Fast CGI backend (web server to CGI handler) library"

PR = "r4"
PV = "2.4.0"

SRC_URI = "http://www.fastcgi.com/dist/fcgi-${PV}.tar.gz \
 file://link-against-math.patch;patch=1 \
 file://cstdio.patch;patch=1 "

SRC_URI[md5sum] = "d15060a813b91383a9f3c66faf84867e"
SRC_URI[sha256sum] = "66fc45c6b36a21bf2fbbb68e90f780cc21a9da1fffbae75e76d2b4402d3f05b9"

LIC_FILES_CHKSUM = "file://LICENSE.TERMS;md5=e3aacac3a647af6e7e31f181cda0a06a"

S = "${WORKDIR}/fcgi-${PV}"

LEAD_SONAME = "libfcgi.so*"

PARALLEL_MAKE=""

inherit autotools pkgconfig
