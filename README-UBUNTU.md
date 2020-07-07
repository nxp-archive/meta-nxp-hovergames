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
