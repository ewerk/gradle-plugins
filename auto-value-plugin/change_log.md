# Change log

## 1.0.0
* Initial version

## 1.0.1
* Removed the `delete` DSL parameter
* Added task `CleanAutoValueSourcesDir`
* Make sure `clean` also calls `CleanAutoValueSourcesDir` via `dependsOn`
* Changed default generated sources dir to `src/auto-value/java` for not interfering with other generated code

## 1.0.2
* minor tweaks to the plugins cleaning logic

## 1.0.3
* current SNAPSHOT