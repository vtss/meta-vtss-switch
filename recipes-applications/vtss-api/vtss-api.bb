DESCRIPTION = "Vitesse Unified Switch API"
SECTION = "libs/network"
LICENSE = "Proprietary"
RPROVIDES_${PN} = "native/vtss-api"

PR      = "r1"
PV      = "4_60a"

LIC_FILES_CHKSUM = "file://COPYING;md5=7b66b1d142643bca86a4723f577dc130"

inherit cmake

SRC_URI = "file://API_${PV}.tar.gz"
S = "${WORKDIR}/API_${PV}/vtss_api"

OECMAKE_BUILDPATH  = "${WORKDIR}/build"
OECMAKE_SOURCEPATH = "${S}"

EXTRA_OECMAKE = "-DVTSS_PHY_API_ONLY=OFF             \
                 -DVTSS_FEATURE_MACSEC=OFF           \
                 -DVTSS_OPT_FDMA=OFF                 \
                 -DVTSS_APPL_MINI=ON                 \
                 -DLIB_INSTALL_DIR:STRING=${baselib} ${VTSS_API_DEFINES}"
EXTRA_OEMAKE  = "-C ${OECMAKE_BUILDPATH}"

VTSS_API_DEFINES_serval1 =        "  -DVTSS_PRODUCT_CHIP=SERVAL          \
                                     -DVTSS_OPT_PORT_COUNT=12            \
                                     -DVTSS_PRODUCT_HW=BOARD_SERVAL_REF  \
                                     -DVTSS_CHIP_10G_PHY=OFF             \
                                  "

VTSS_API_DEFINES_luton26 =        "  -DVTSS_PRODUCT_CHIP=SPARX_III_26    \
                                     -DVTSS_OPT_PORT_COUNT=26            \
                                     -DVTSS_PRODUCT_HW=BOARD_LUTON26_REF \
                                     -DVTSS_CHIP_10G_PHY=OFF             \
                                  "

VTSS_API_DEFINES_luton10 =        "  -DVTSS_PRODUCT_CHIP=SPARX_III_10    \
                                     -DVTSS_OPT_PORT_COUNT=10            \
                                     -DVTSS_PRODUCT_HW=BOARD_LUTON10_REF \
                                     -DVTSS_CHIP_10G_PHY=OFF             \
                                  "

VTSS_API_DEFINES_jaguar1-cu24 =   " -DVTSS_PRODUCT_CHIP=JAGUAR_1         \
                                    -DVTSS_OPT_PORT_COUNT=29             \      
                                    -DVTSS_PRODUCT_HW=BOARD_JAGUAR1_REF  \
                                  "

VTSS_API_DEFINES_jaguar1-cu48 =   " -DVTSS_PRODUCT_CHIP=E_STAX_III_68_DUAL \
                                    -DVTSS_OPT_PORT_COUNT=53               \
                                    -DVTSS_PRODUCT_HW=BOARD_JAGUAR1_REF    \
                                  "

VTSS_API_DEFINES_serval2 =        " -DVTSS_PRODUCT_CHIP=SERVAL_2         \
                                    -DVTSS_PRODUCT_HW=BOARD_JAGUAR2_REF  \
                                    -DVTSS_OPT_PORT_COUNT=15             \
                                  "

VTSS_API_DEFINES_jaguar2-cu24 =   " -DVTSS_PRODUCT_CHIP=JAGUAR_2         \
                                    -DVTSS_PRODUCT_HW=BOARD_JAGUAR2_REF  \
                                    -DVTSS_OPT_PORT_COUNT=29             \
                                  "

VTSS_API_DEFINES_jaguar2-cu48 =   " -DVTSS_PRODUCT_CHIP=JAGUAR_2         \
                                    -DVTSS_PRODUCT_HW=BOARD_JAGUAR2_REF  \
                                    -DVTSS_OPT_PORT_COUNT=51             \
                                  "

FILES_${PN}-dev += "/usr/share/vtss_api/cmake/*"
PKG_${PN} = "${PN}-${MACHINE}"
PKG_${PN}-dev = "${PN}-${MACHINE}-dev"
PKG_${PN}-dbg = "${PN}-${MACHINE}-dbg"
