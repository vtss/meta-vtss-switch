DESCRIPTION = "Vitesse Unified Switch API"
SECTION = "libs/network"
LICENSE = "Proprietary"
RPROVIDES_${PN} = "native/vtss-api"

SRCREV  = "0c55c44eca87"
PR      = "r3"
PV      = "0.1"

LIC_FILES_CHKSUM = "file://COPYING;md5=7b66b1d142643bca86a4723f577dc130"

inherit cmake

SRC_URI = "hg://soft02.dk.vitesse.com/hg/;protocol=http;module=project/vtss_api"

S = "${WORKDIR}/project/vtss_api"

OECMAKE_BUILDPATH  = "${WORKDIR}/build"
OECMAKE_SOURCEPATH = "${S}"

EXTRA_OECMAKE = "-DVTSS_PHY_API_ONLY=OFF             \
                 -DVTSS_FEATURE_MACSEC=OFF           \
                 -DVTSS_OPT_FDMA=OFF                 \
                 -DVTSS_APPL_MINI=ON                 \
                 -DLIB_INSTALL_DIR:STRING=${baselib}"
EXTRA_OEMAKE  = "-C ${OECMAKE_BUILDPATH}"

EXTRA_OECMAKE_prepend_serval1 = "                                 \
                              -DVTSS_PRODUCT_CHIP=SERVAL          \
                              -DVTSS_OPT_PORT_COUNT=12            \
                              -DVTSS_PRODUCT_HW=BOARD_SERVAL_REF  \
                              -DVTSS_CHIP_10G_PHY=OFF             \
                              "
EXTRA_OECMAKE_prepend_luton26 = " \
                              -DVTSS_PRODUCT_CHIP=SPARX_III_26    \
                              -DVTSS_OPT_PORT_COUNT=26            \
                              -DVTSS_PRODUCT_HW=BOARD_LUTON26_REF \
                              -DVTSS_CHIP_10G_PHY=OFF             \
                              "
EXTRA_OECMAKE_prepend_jaguar1 = "                                 \
                              -DVTSS_PRODUCT_CHIP=JAGUAR_1        \
                              -DVTSS_OPT_PORT_COUNT=29            \
                              -DVTSS_PRODUCT_HW=BOARD_JAGUAR1_REF \
                              "

FILES_${PN}-dev += "/usr/share/vtss_api/cmake/*"
PKG_${PN} = "${PN}-${MACHINE}"
PKG_${PN}-dev = "${PN}-${MACHINE}-dev"
PKG_${PN}-dbg = "${PN}-${MACHINE}-dbg"
