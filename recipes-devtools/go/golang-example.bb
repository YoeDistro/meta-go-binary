# Copyright (C) 2020 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "This is a simple example recipe that cross-compiles a Go program."
SECTION = "examples"
HOMEPAGE = "https://golang.org/"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

GO_IMPORT = "github.com/golang/example"
SRC_URI = "git://${GO_IMPORT}"
SRCREV = "46695d81d1fae905a270fb7db8a4d11a334562fe"
UPSTREAM_CHECK_COMMITS = "1"

DEPENDS = "golang-native"

inherit goarch

S = "${WORKDIR}/git"

do_configure() {
    mkdir -p ${WORKDIR}/src/`dirname ${GO_IMPORT}`
    ln -sf ${S} ${WORKDIR}/src/${GO_IMPORT}
}

# This is just to make clear where this example is
do_compile() {
    GOARCH=${TARGET_GOARCH} GOPATH=${WORKDIR} go build -o hello ${WORKDIR}/src/${GO_IMPORT}/hello/hello.go
}

do_install() {
    install -Dm 0755 ${WORKDIR}/src/${GO_IMPORT}/hello/hello ${D}${bindir}/hello
}
