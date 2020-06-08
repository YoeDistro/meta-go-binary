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
SRC_URI[amd64.sha256sum] = "aed845e4185a0b2a3c3d5e1d0a35491702c55889192bb9c30e67a3de6849c067"
SRC_URI[arm64.sha256sum] = "05dc46ada4e23a1f58e72349f7c366aae2e9c7a7f1e7653095538bc5bba5e077
"

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
