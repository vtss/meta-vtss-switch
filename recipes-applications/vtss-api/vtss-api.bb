DESCRIPTION = "Vitesse Unified Switch API"
SECTION = "libs/network"
LICENSE = "Proprietary"
RPROVIDES_${PN} = "native/vtss-api"

SRCREV  = "b2ea9f20396b"
PR      = "r1"
PV      = "0.1"

LIC_FILES_CHKSUM = "file://COPYING;md5=7b66b1d142643bca86a4723f577dc130"

inherit cmake

SRC_URI = "hg://soft02.dk.vitesse.com/hg/;protocol=http;module=project/vtss_api"

S = "${WORKDIR}/project/vtss_api"

OECMAKE_BUILDPATH  = "${WORKDIR}/build"
OECMAKE_SOURCEPATH = "${S}"

EXTRA_OECMAKE = "-DVTSS_TARGET_NAME=SERVAL1          \
                 -DVTSS_PHY_API_ONLY=off             \
                 -DVTSS_CHIP_10G_PHY=OFF             \
                 -DVTSS_FEATURE_MACSEC=OFF           \
                 -DLIB_INSTALL_DIR:STRING=${baselib} \
                 -DVTSS_APPL_MINI=ON"
EXTRA_OEMAKE  = "-C ${OECMAKE_BUILDPATH}"

FILES_${PN}-dev += "/usr/share/vtss_api/cmake/*"
