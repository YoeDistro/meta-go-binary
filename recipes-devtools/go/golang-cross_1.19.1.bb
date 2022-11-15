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

make_wrapper() {
        rm -f ${D}${bindir}/$2
        mkdir -p ${D}${bindir}
        cat <<END >${D}${bindir}/$2
#!/bin/bash
here=\`dirname \$0\`
export GOARCH="${TARGET_GOARCH}"
export CGO_ENABLED="0"
#unset GOROOT
export GOOS="${TARGET_GOOS}"
export GOARM="\${GOARM:-${TARGET_GOARM}}"
export GO386="\${GO386:-${TARGET_GO386}}"
export GOMIPS="\${GOMIPS:-${TARGET_GOMIPS}}"
\$here/../../../bin/$1 "\$@"
END
        chmod +x ${D}${bindir}/$2
}

do_install() {
    for f in go gofmt
    do
        make_wrapper $f ${TARGET_PREFIX}$f
    done
}
