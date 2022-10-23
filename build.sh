#!/bin/bash -x

date
whoami
pwd
ls -al
git --version
java -version

git config --global user.email "git@github.com"
git config --global user.name "build.sh"

cid=$(git rev-parse HEAD)

git show-ref
git branch -avv
git status
ls -l ./.git/refs/heads/

git checkout docs
git rebase main
mkdir -p docs
ls -l docs
./gradlew run -q >docs/index.html
ls -l docs
git add docs
git status
git diff --cached
git commit -m "auto build of $cid"
git push
