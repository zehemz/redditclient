# Top post reddit client interview test :rocket:

[Test description](https://github.com/deviget/Android)

### Template: Master/Detail flow from android studio templates.

[Base architecture](https://github.com/googlesamples/android-architecture-components/)

- MVVM ARQ.
- Kotlin.
- Single activity navigation.
- UI and Unit tests to show.
- DI with dagger2.
- Retrofit 2 for network requests.
- Room for disk info persistence.
- Glide for load images.

### Guidelines:

- [x] Assume latest Android platform
- [x] Support Landscape

### Required features completed 5 hours development:

- [x] Dismiss Post Button (remove the cell from list. Animations required)
- [x] Dismiss All Button (remove all posts. Animations required)
- [x] Support split layout (left side: all posts / right side: detail post)
- [x] App state-preservation/restoration
- [ ] Indicator of unread/read post (updated status, after post it’s selected)
- [ ] Pull to Refresh
- [ ] Pagination support
- [ ] Saving pictures in the picture gallery

### What to show
- [x] Title (at its full length, so take this into account when sizing your cells).
- [x] Author.
- [ ] entry date, following a format like “x hours ago”.
- [x] A thumbnail for those who have a picture.
- [x] Number of comments.
- [ ] Unread status.

### Detailed time line
- Create template from android studio. (0 min)
- Downloading dependencies and android images and run template. (30 min)
- Write base directory structure + util classes. (15 min)
- added gradle script to allow easy import of most used libraries, added basic DI, network and support libs. (15 min)
- TDD approach, prepare base application injection tree, db model, viewmodel, executors and net config. (1:30 hr)
- No pagination but limit 50 reddit posts (5 minutes)
- Rename of files, added right navigation and fragment states, added viewmodel for reddit posts (1:30 hr)
- Added Glide for image cache and presentation from url. Some style modifications and enter/exit transition (15 min)
- Added dismiss buttons. (25 min, exceeded 10). 

### Non required additional features to add
- [ ] Error handling messages
- [ ] Better looks and transition
- [ ] Full coverage report (CI)
- [ ] Full lint report (CI)
- [ ] CD
- [ ] README badges :octocat:
