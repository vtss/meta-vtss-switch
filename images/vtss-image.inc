require recipes-core/images/core-image-minimal.bb

inherit perlnative

IMAGE_INSTALL += " mtd-utils gdbserver libunwind "

LICENSE = "MIT"

IMAGE_FSTYPES = "squashfs-xz"

IMAGE_DEPENDS_squashfs-xz = "squashfs-tools-native virtual/kernel vtss-mkimage-native"

do_rootfs_append() {
    bb.build.exec_func('do_combine_image', d)
}

# Need this from kernel recipe...?
LINUX_VERSION = "3.14"

CHIP_FAMILY_vcoreiii     = "0"
CHIP_FAMILY_luton28      = "1"
CHIP_FAMILY_luton26      = "2"
CHIP_FAMILY_jaguar1      = "3"
CHIP_FAMILY_jaguar1-dual = "3"
CHIP_FAMILY_serval1      = "4"
CHIP_FAMILY_jaguar2      = "5"
CHIP_FAMILY_servalt      = "6"

do_combine_image() {
    K=${DEPLOY_DIR_IMAGE}/vmlinux-${LINUX_VERSION}.${MACHINE}.xz
    R=${DEPLOY_DIR_IMAGE}/${PN}-${MACHINE}.squashfs-xz
    I=${DEPLOY_DIR_IMAGE}/${PN}-${MACHINE}.img
    [ -f $K ] && vtss-mkimage.pl --machine=${MACHINE} --soc=${SOC_FAMILY} --socfam=${CHIP_FAMILY} --kernel=$K --rootfs=$R --out=$I
    exit 0
}

# Don't grok GPLv3
INCOMPATIBLE_LICENSE = "GPL-3.0 LGPL-3.0 AGPL-3.0"
