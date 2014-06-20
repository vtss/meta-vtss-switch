require recipes-core/images/core-image-minimal.bb

IMAGE_INSTALL += " vtss-api aufs-util mtd-utils"

LICENSE = "MIT"

IMAGE_FSTYPES ?= "squashfs-xz jffs2 tar.gz"

IMAGE_DEPENDS_squashfs-xz = "squashfs-tools-native"
