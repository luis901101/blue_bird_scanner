The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

Types of changes
- `Added` for new features.
- `Changed` for changes in existing functionality.
- `Deprecated` for soon-to-be removed features.
- `Removed` for now removed features.
- `Fixed` for any bug fixes.
- `Security` in case of vulnerabilities.

## 3.0.0+1 (2022-10-24)
### Added
- Added support of namespace property to support Android Gradle Plugin (AGP) 8. Projects with AGP < 4.2 should be compatible as well but it is highly recommended to update at least to AGP 7.0 or newer.

### Changed
- Example project updated to work with AGP 8

## 2.0.1+5 (2021-09-13)
### Changed
- Android plugin API updated to support v2 Embedding while remaining compatible with apps that don’t use the v2 Android embedding.

## 2.0.0+4 (2021-04-15)
### Added
- Support for null-safety

## 1.0.1+3 (2020-10-10)
### Fixed
- Support for latest flutter frameworks

## 1.0.0+2
### Fixed
- Some bugs fixed