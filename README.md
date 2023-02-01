# Android Trending Github repos Challenge

The task is to implement an app that will display Github most starred repos, the app
will fetch JSON data directly from the Github API.
It is not designed to be difficult, so be mindful of over-engineering your solution.
There is no strict time limit for the project, but we would expect that it wouldn't take
you more than a few hours to complete.
Requirements
● As a user, I should be able to list the most starred Github repos that were
created in the last 30 days.
● As a user, I should see the results presented as a vertical list. Each row
represents a repo.
● As a user, I should be able to see the repo name, description, stars count,
username and user avatar on each row.
● As a user, I should be able to keep scrolling and see new results coming up
(pagination).

## Installation
Clone this repository and import into **Android Studio**
```bash
git clone git@github.com:AAlier/TrendingGithubRepo.git
```

## Build variants
Use the Android Studio *Build Variants* button to choose between **release** and **debug** flavors combined with debug and release build types


## Generating signed APK
From Android Studio:
1. ***Build*** menu
2. ***Generate Signed APK...***
3. Fill in the keystore information *(you only need to do this once manually and then let Android Studio remember it)*

## Maintainers
This project is mantained by:
* [Alier](http://github.com/AAlier)


## Contributing

1. Fork it
2. Create your feature branch (git checkout -b my-new-feature)
3. Commit your changes (git commit -m 'Add some feature')
4. Run the linter (ruby lint.rb').
5. Push your branch (git push origin my-new-feature)
6. Create a new Pull Request

## Dependencies
|Libraries| Usage |
|--|--|
|[Retrofit](https://square.github.io/retrofit/)| A type-safe HTTP client for Android and Java. For more information please see the website. Handling of the |
|[Koin](https://github.com/InsertKoinIO/koin-getting-started/blob/main/README.md)|A pragmatic lightweight dependency injection framework for Kotlin developers.|
|[Glide](https://github.com/bumptech/glide/blob/master/README.md)| Fast and efficient open source media management and image loading framework for Android |