#!/bin/bash
# $1 = username@host
# $2 = port
cd target/ || exit
sftp -o PasswordAuthentication=no -P "$2" "$1" <<END
cd plugins
rm MinecraftManhunt*.jar
put MinecraftManhunt*.jar
END