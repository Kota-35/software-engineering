#!/bin/bash

# 引数の取得（位置引数）
GROUP_ID=${1:-"com.example"}
ARTIFACT_ID=${1:-"my-project"}
ARCHETYPE=${2:-"maven-archetype-quickstart"}

echo "Mavenプロジェクトを作成中..."
echo "GroupId: $GROUP_ID"
echo "ArtifactId: $ARTIFACT_ID"
echo "Archetype: $ARCHETYPE"

mvn archetype:generate \
    -DgroupId="$GROUP_ID" \
    -DartifactId="$ARTIFACT_ID" \
    -DarchetypeArtifactId="$ARCHETYPE" \
    -DinteractiveMode=false