# Copyright (C) 2020 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Golang Compiler"
HOMEPAGE = "https://golang.org/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
SECTION = "devel"
DEPENDS = ""

ARCH_x86-64 = "amd64"
ARCH_x86 = "amd64"
ARCH_aarch64 = "arm64"

SRC_URI = "https://dl.google.com/go/go${PV}.linux-amd64.tar.gz;name=amd64;subdir=amd64 \
           https://dl.google.com/go/go${PV}.linux-arm64.tar.gz;name=arm64;subdir=arm64 \
           https://dl.google.com/go/go${PV}.linux-386.tar.gz;name=386;subdir=386 \
          "
SRC_URI[amd64.sha256sum] = "6272d6e940ecb71ea5636ddb5fab3933e087c1356173c61f4a803895e947ebb3"
SRC_URI[arm64.sha256sum] = "bb6d22fe5806352c3d0826676654e09b6e41eb1af52e8d506d3fa85adf7f8d88"
SRC_URI[386.sha256sum] = "cab5f51e6ffb616c6ee963c3d0650ca4e3c4108307c44f2baf233fcb8ff098f6"

inherit bin_package

S = "${WORKDIR}"

do_install () {
    install -d ${D}${root_prefix}
    cp --preserve=mode,timestamps -R ${S}/${ARCH}/go/* ${D}${root_prefix}
}

INSANE_SKIP_${PN} += "already-stripped"
BBCLASSEXTEND = "native nativesdk"

COMPATIBLE_HOST_class-target = "null"


PROVIDES_class-native = "go-native"
PROVIDES_class-nativesdk = "virtual/${TARGET_PREFIX}go-crosssdk virtual/${TARGET_PREFIX}go-runtime"
