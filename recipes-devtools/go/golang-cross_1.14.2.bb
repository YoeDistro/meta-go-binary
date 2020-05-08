# Copyright (C) 2020 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

inherit cross

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PROVIDES = "virtual/${TUNE_PKGARCH}-go"
DEPENDS = "go-native"

PN = "go-cross-${TUNE_PKGARCH}"

export GOCACHE = "${B}/.cache"
CC = "${@d.getVar('BUILD_CC').strip()}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_install[noexec] = "1"
