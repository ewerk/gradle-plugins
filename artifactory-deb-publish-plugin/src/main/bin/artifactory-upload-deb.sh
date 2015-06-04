#!/usr/bin/env bash

if [ $# -ne 8 ]
  then
    echo "Usage: $0 <url> <user> <pass> <repo_key> <distribution> <component> <arch> <file>"
    exit 1
fi

URL=$1
USER=$2
PASS=$3
REPO_KEY=$4
DISTRIBUTION=$5
COMPONENT=$6
ARCH=$7
FILE=$8

BASE=$(basename ${FILE})
PREFIX=`echo ${BASE} | cut -c 1`
CHECKSUM=`/usr/bin/openssl sha1 ${FILE} | cut -f 2 -d ' '`

echo "Publishing: ${FILE}"
echo "SHA-1: ${CHECKSUM}"

curl \
  -s \
  -H "Accept: application/vnd.org.jfrog.artifactory.storage.ItemCreated+json" \
  -H "X-Checksum-Sha1: ${CHECKSUM}" \
  -u${USER}:${PASS} \
  --data-binary @${FILE} \
  -XPUT \
  "${URL}/${REPO_KEY}/pool/${COMPONENT}/${PREFIX}/${BASE};deb.distribution=${DISTRIBUTION};deb.component=${COMPONENT};deb.architecture=${ARCH}"