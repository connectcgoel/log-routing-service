#!/bin/bash
aws ecr get-login-password --region ap-south-1 | docker login --username AWS --password-stdin 916694224539.dkr.ecr.ap-south-1.amazonaws.com
tagName="$1"
if [ -z "$1" ]
then
  tagName="latest"
fi
export TAG=$tagName
docker-compose up -d
