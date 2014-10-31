DESCRIPTION = "SMBStaX for Linux"
SECTION = "libs/network"
LICENSE = "Proprietary"
DEPENDS = "vtss-api libfcgi"

SRCREV  = "27427c44a126"
PR      = "r6"
PV      = "0.1"

LIC_FILES_CHKSUM = "file://COPYING;md5=382ac918ae415b39bcc77838ac8c1ea7"

SRC_URI = "hg://soft02.dk.vitesse.com/hg/;protocol=http;module=project/webstax2"
SRC_URI += "file://vtss_smbstax.sh"
SRC_URI += "file://icli"
SRC_URI += "file://default-config"

FILES_${PN} += "/usr/doc/*"

CONFIG_SETUP_serval1 = "Serval,SMBSTAX,SERVAL,BOARD_SERVAL_REF,yocto"
CONFIG_SETUP_serval2 = "Jaguar2,SMBSTAX,STANDALONE,SERVAL_2,,yocto"

S = "${WORKDIR}/project/webstax2"

do_patch_append() {
    bb.build.exec_func('do_nuke_api', d)
}

do_nuke_api () {
        rm -fr vtss_api
}

do_configure () {
 cd build
 cat > config.mk <<EOF
VTSS_API_EXTERNAL = 1
include \$(BUILD)/make/templates/linuxSwitch.in
\$(eval \$(call linuxSwitch/${CONFIG_SETUP}))
\$(eval \$(call linuxSwitch/Build))
EOF
}

do_compile () {
 cd build
 oe_runmake CC="${CC}" CPPFLAGS="${CPPFLAGS}" V=1
}

do_install () {
    install -d ${D}${bindir}/
    install -c -m 755 build/obj/SMBStaX.elf ${D}${bindir}/vtss_smbstax
    install -c -m 755 ${WORKDIR}/icli ${D}${bindir}/
    install -d ${D}/etc/init.d/
    install -c -m 755 ${WORKDIR}/vtss_smbstax.sh ${D}/etc/init.d/
    install -d ${D}/etc/default/switch
    install -c -m 755 ${WORKDIR}/default-config ${D}/etc/default/switch
    install -d ${D}/usr/doc/switch
    install -c -m 444 build/obj/*.htm ${D}/usr/doc/switch
}

pkg_postinst_${PN} () {
if [ x"$D" = "x" ]; then
#    update-rc.d vtss_smbstax.sh defaults
    grep ^cli: /etc/group || {
        addgroup cli
    }
    grep ^admin: /etc/passwd || {
        adduser --disabled-password -s /usr/bin/icli -g "Admin CLI User" -G cli admin
#        echo "admin:" | chpasswd -e
    }
else
    exit 1
fi
}
