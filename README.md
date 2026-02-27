**[ The tool and this document is currently still early in development. ]**

# Minebuilder Modding Tool

Minebuilder Modding Tool is a desktop application that simplifies creating and editing mods for Minebuilder.

The goal of this tool is to make Minebuilder modding accessible and less error-prone by replacing manual file editing 
with a graphical interface.

Instead of manually editing multiple XML files and keeping track of IDs and filenames, the tool manages everything
internally and exports a ready-to-use mod file.

---

# Features

## Current Features

* display list of items and update values (saving not implemented yet)

## Planned Features

* full modding support
    * blocks
    * items
    * crafting recipes
    * biomes
    * ores
    * structures
    * mobs
* export mod
* merge multiple mods

---

# Requirements

* Java 17 or newer
* Windows, Linux or macOS

---

# Installation

Download the latest `.jar` file and run:

```
java -jar MinebuilderModTool.jar
```

No installation required.

---

# Configuration

The tool uses a configuration file named:

```
application.properties
```

The file must be placed in the same directory as the `.jar` file.

## Mod Root Directory

Defines the `files` directory of the Minebuilder mod.

This directory contains all modifiable Minebuilder content.

Relative and absolute paths are supported.

### Relative Path

```PROPERTIES
mod.directory = ./files
```

### Absolute Path

```PROPERTIES
mod.directory = C:/Users/test/Mods/files
```

### Default Value

```PROPERTIES
mod.directory = ./files
```

If the directory is invalid, the tool will show an error message during startup.

---

# Getting Started

## 1. Prepare the files Folder

Minebuilder mods are based on a directory called `files`.

You can either:

* Use an existing mod
* Copy the files folder from Minebuilder
* Download a vanilla files folder (link)

Example structure:

```
files/
 ├── Armor/
 ├── Data/
 ├── Interface/
 ├── Items/
 ├── Models/
 ├── Music/
 ├── Scripts/
 ├── Shaders/
 ├── Sounds/
 ├── Structures/
 ├── Terrain/
 ├── Textures/
 ├── settings.bin
 └── version
```

## 2. Configure the Tool

Create a file named:

```
application.properties
```

Example:

```PROPERTIES
mod.directory = ./files
```

## 3. Start the Tool

Run:

```BASH
java -jar MinebuilderModTool.jar
```

The mod will be loaded automatically and you can start editing.

---

# How Modding Works

Minebuilder mods are primarily based on:

* XML files (blocks, items, crafting, mobs, biomes)
* Script files (JavaScript-based mob behaviour)
* Textures
* Models
* Structures

Traditionally, modding requires manually editing multiple files and keeping track of IDs and filenames and even using a
server with WorldEdit to export new structures.

This tool automates these tasks and reduces the chance of errors.

---

# Supported Game Versions

Currently supported:

* Minebuilder 1.14.3

New versions will be supported when available.

Older versions can be supported on request.

---

# Planned Architecture

The tool internally converts Minebuilder XML files into an internal data format.

This allows:

* Easier editing
* Validation
* Consistent exports

The final export produces a ready-to-use Minebuilder mod.

---

# Contributing

This project will become open source in the future.

Pull requests and suggestions will be welcome.

---

# Motivation

Minebuilder modding currently requires manual editing of multiple files.

This process is slow and error-prone.

Minebuilder Modding Tool aims to make modding faster, safer and more accessible.
