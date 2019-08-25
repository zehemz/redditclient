# Top reddit posts interview test

[Test description](https://github.com/deviget/Android)

### Template: Master/Detail flow from android studio templates.

[Base architecture](https://github.com/googlesamples/android-architecture-components/)

- DI with dagger2.
- Retrofit 2 for network requests.
- Additionally added database persistence with Room.
- Single activity navigation.
- Glide for load images.
- UI and Unit tests to show.

### Detailed time line
- Create template from android studio. (0 min)
- Downloading dependencies and android images and run template. (30 min)
- Write base directory structure + util classes. (15 min)
- added gradle script to allow easy import of most used libraries, added basic DI, network and support libs. (15 min)
- TDD approach, prepare base application injection tree, db model, viewmodel, executors and net config. (1:30 hr)
- No pagination but limit 50 reddit posts (5 minutes)
- Rename of files, added right navigation and fragment states, added viewmodel for reddit posts (1:30 hr)
- Added Glide for image cache and presentation from url. Some style modifications and enter/exit transition (15 min)