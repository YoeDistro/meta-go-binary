# meta-go-binary
Go language binary toolchains for OE/Yocto

This repo tends to provide cross compiler for go programs based on upstream binary relesases
each release will be tracked in its own branch e.g. if its 1.14.x is needed to be used then checkout 1.14 branch

in `local.conf` add

```
PREFERRED_PROVIDER_go-native = "golang-native"
PREFERRED_PROVIDER_virtual/${TARGET_PREFIX}go-runtime = "golang-runtime"
CGO_ENABLED = "0"

```

Try building example

```
IMAGE_INSTALL_append = " golang-example"
bitbake yoe-simple-image

```

Flash `yoe-simple-image` or if using qemu machine then simply run

```
runqemu nographic

```

After booting on shell prompt run

```
$ hello
Hello, Go examples!

```

Maintainer: Khem Raj <raj.khem@gmail.com>

