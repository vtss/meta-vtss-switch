DESCRIPTION = "SMBStaX for Linux"
SECTION = "libs/network"
LICENSE = "Proprietary"
DEPENDS = "vtss-api libfcgi"

SRCREV  = "0f0fe0cf4cf2"
PR      = "r4"
PV      = "0.1"

LIC_FILES_CHKSUM = "file://COPYING;md5=382ac918ae415b39bcc77838ac8c1ea7"

SRC_URI = "hg://soft02.dk.vitesse.com/hg/;protocol=http;module=project/webstax2"
SRC_URI += "file://vtss_smbstax.sh"
SRC_URI += "file://icli"
SRC_URI += "file://default-config"

FILES_${PN} += "/usr/doc/*"

S = "${WORKDIR}/project/webstax2"

do_patch_append() {
    bb.build.exec_func('do_nuke_api', d)
}

do_nuke_api () {
        rm -fr vtss_api
}

do_configure_append_serval1 () {
 cd build
 ln -sf configs/smb_switch_serval_ref_linux_icpu.mk config.mk
}

do_configure_append_jaguar1 () {
 cd build
 ln -sf configs/smb_switch_jr1_ref_linux_icpu.mk config.mk
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
