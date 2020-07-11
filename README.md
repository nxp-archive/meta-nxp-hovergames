i.MX Linux Yocto Project BSP for HoverGames
===========================================

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
$ repo init -u https://source.codeaurora.org/external/imx/imx-manifest -b imx-linux-zeus -m imx-5.4.24-2.1.0.xml
$ repo sync
```

Download meta-ros and meta-nxp-hovergames
-----------------------------------------

```
$ git clone https://github.com/ros/meta-ros.git sources/meta-ros
$ git clone https://github.com/NXPmicro/meta-nxp-hovergames.git sources/meta-nxp-hovergames
```

Create a _new_ build folder
---------------------------

If you want to create a _new_ build folder:

```
$ DISTRO=fsl-imx-xwayland MACHINE=imx8mmnavq source setup-hovergames -b build
```

Use an _existing_ build folder
----------------------------

If you want to build in an _existing_ build folder:

```
$ cd hovergames
$ source setup-environment build
```

Build the image
---------------

There is a regular image and a minimal image. The minimal image excludes
build-on-target support and the weston desktop.

```
$ bitbake imx-image-hovergames
$ bitbake imx-image-hovergames-minimal
```
