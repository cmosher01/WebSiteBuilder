#!/bin/bash -x

date
whoami
pwd
ls -al
git --version
java -version

cid=$(git rev-parse HEAD)

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
