(defproject org.zalando.stups/essentials "0.1.0-SNAPSHOT"
            :description "Microservice for resource types and scopes."
            :url "https://github.com/zalando-stups/essentials"

            :license {:name "The Apache License, Version 2.0"
                      :url  "http://www.apache.org/licenses/LICENSE-2.0"}

            :min-lein-version "2.0.0"

            :dependencies [[org.clojure/clojure "1.6.0"]
                           [org.zalando.stups/friboo "0.8.0"]
                           [yesql "0.5.0-rc2"]
                           [org.postgresql/postgresql "9.3-1102-jdbc41"]]

            :main ^:skip-aot org.zalando.stups.essentials.core
            :uberjar-name "essentials.jar"

            :plugins [[io.sarnowski/lein-docker "1.1.0"]]

            :docker {:image-name "stups/essentials"}

            :release-tasks [["vcs" "assert-committed"]
                            ["change" "version" "leiningen.release/bump-version" "release"]
                            ["vcs" "commit"]
                            ["vcs" "tag"]
                            ["clean"]
                            ["uberjar"]
                            ["docker" "build"]
                            ["docker" "push"]
                            ["change" "version" "leiningen.release/bump-version"]
                            ["vcs" "commit"]
                            ["vcs" "push"]]

            :profiles {:uberjar {:aot :all}

                       :dev     {:repl-options {:init-ns user}
                                 :source-paths ["dev"]
                                 :dependencies [[org.clojure/tools.namespace "0.2.10"]
                                                [org.clojure/java.classpath "0.2.2"]]}})