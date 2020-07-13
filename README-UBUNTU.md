Desktop-mix-poky branch
-----------------------

This branch is modified so that Yocto recipes (gstreamer, weston, vivante drivers) can be installed next to the Ubuntu rootfs.

Note there's a manual workaround need to get the graphics subsystem installed, where fontcache post-install step fails. A hotfix is shown below
```
diff --git a/meta/recipes-graphics/ttf-fonts/liberation-fonts_2.00.1.bb b/meta/recipes-graphics/ttf-fonts/liberation-fonts_2.00.1.bb
index f5df9efa3b..82e6488067 100644
--- a/meta/recipes-graphics/ttf-fonts/liberation-fonts_2.00.1.bb
+++ b/meta/recipes-graphics/ttf-fonts/liberation-fonts_2.00.1.bb
@@ -10,7 +10,7 @@ LICENSE = "OFL-1.1"
 LIC_FILES_CHKSUM = "file://LICENSE;md5=f96db970a9a46c5369142b99f530366b"
 PE = "1"

-inherit allarch fontcache
+inherit allarch

 FONT_PACKAGES = "${PN}"
```
This disables the fontcache in the liberation-fonts recipe thus enabling a succesfull build



i.MX Linux Yocto Project BSP for HoverGames
===========================================

This README contains instructions for setting up a Yocto build
for the HoverGames image with Ubuntu rootfs.

Install the `repo` utility
--------------------------

To get the BSP you need to have `repo` installed.

```
$ mkdir ~/bin
$ curl https://storage.googleapis.com/git-repo-downloads/repo  > ~/bin/repo
$ chmod a+x ~/bin/repo
$ PATH=${PATH}:~/bin
```

Download the Yocto Project BSP
------------------------------

```
$ mkdir hovergames
$ cd hovergames
$ repo init -u ssh://bitbucket.sw.nxp.com/imx/imx-manifest -b linux-zeus-internal -m int-5.4.24-2.1.0_hovergames.xml
$ repo sync
```

Create a _new_ build folder
---------------------------

If you want to create a _new_ build folder:

```
$ DISTRO=imx-desktop-xwayland MACHINE=imx8mmnavqubuntu source hovergames-setup.sh -b build-ubuntu
```

Use an _existing_ build folder
------------------------------

If you want to build in an _existing_ build folder:

```
$ cd hovergames
$ source setup-environment build-ubuntu
```

Build the image
---------------

```
$ bitbake imx-image-hovergames-ubuntu
```
