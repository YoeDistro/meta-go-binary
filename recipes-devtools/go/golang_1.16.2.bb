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
          "
SRC_URI[amd64.sha256sum] = "542e936b19542e62679766194364f45141fde55169db2d8d01046555ca9eb4b8"
SRC_URI[arm64.sha256sum] = "6924601d998a0917694fd14261347e3798bd2ad6b13c4d7f2edd70c9d57f62ab"

inherit bin_package

S = "${WORKDIR}"

do_install () {
    install -d ${D}${root_prefix}
    cp --preserve=mode,timestamps -R ${S}/${ARCH}/go/* ${D}${root_prefix}
}

SYSROOT_DIRS_NATIVE += "/"
INHIBIT_SYSROOT_STRIP = "1"

INSANE_SKIP_${PN} += "already-stripped"
BBCLASSEXTEND = "native nativesdk"

COMPATIBLE_HOST_class-target = "null"

PROVIDES_class-native = "go-native"
PROVIDES_class-nativesdk = "virtual/${TARGET_PREFIX}go-crosssdk virtual/${TARGET_PREFIX}go-runtime"
