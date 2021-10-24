(defproject lein-ring-compojure-sqlite "0.1.0-SNAPSHOT"
  :description      "FIXME: write description"
  :url              "http://example.com/FIXME"
  :license          {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
                     :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies     [[org.clojure/clojure       "1.10.0"]
                     [org.clojure/tools.logging "0.2.3"]
                     [log4j                     "1.2.16"]

                     ;;The standard JDBC wrapper
                     [org.clojure/java.jdbc     "0.2.0"]

                     ;; Pure Java library
                     [org.xerial/sqlite-jdbc    "3.7.2"]

                     [ring/ring-core            "1.1.0"]
                     [ring/ring-jetty-adapter   "1.1.0"]

                     ;; For RESTful routes
                     [compojure                 "1.1.0"]

                     ;; For JSON
                     [cheshire                  "4.0.0"]

                     [joda-time                 "2.0"]]

  :repl-options     {:init-ns lein-ring-compojure-sqlite.core}

  :dev-dependencies [[ring/ring-devel           "1.1.0"]]

  :plugins          [[lein-ring                 "0.6.6"]]

  :main             lein-ring-compojure-sqlite.core

  :jvm-opts         ["-server"
                     "-Xms32M"
                     "-Xms256M"
                     "-XX:NewRatio=5"]

  ;; User profiles
  :profiles         {:uberjar {:aot :all}}

  ;; Tell ring where to find the main entry point of the application
  :ring            {:handler lein-ring-compojure-sqlite.core/app})