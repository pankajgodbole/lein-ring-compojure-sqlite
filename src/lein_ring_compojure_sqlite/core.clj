(ns lein-ring-compojure-sqlite.core
  (:use [compojure.core]
        [ring.util.response]
        [ring.middleware.session]
        [ring.middleware.session.cookie]
        [ring.middleware.content-type]
        [ring.middleware.params])

  (:require [clojure.string                       :as string]
            [clojure.tools.logging                :as log]
            [compojure.route                      :as route]
            [compojure.handler                    :as cmpjr-hndlr]
            [ring.adapter.jetty                   :as jetty]
            [cheshire.core                        :as json])
            ;;[lein-ring-compojure-sqlite.util      :as util]
            ;;[lein-ring-compojure-sqlite.sqlite    :as sqlite])
            ;;[lein-ring-compojure-sqlite.flat-file :as flat-file]



  (:gen-class :main true))

;;
;;
;;
(defroutes main-routes
  ""
           ;; Default route
           (route/resources "/"))
           ;; End-point to display the index
           ;;(GET "/sqlite" [] sqlite/show-index)

           ;; End-point to create
           ;;(POST "/sqlite/" [] sqlite/create)


;;
;; Force requests to / to be routed to the index.html file.
;;
(defn wrap-dir-index
  "Useful to force requests to / to be routed to the index.html file.
   Takes a handler and returns a function that takes one argument: the ring
   request hash, which contains other hashes. This function calls update-in to
   change the uri of the request: if the uri matches /, it gets changed to
   /index.html."
  [handler]
  (fn [req]
    (handler
      (update-in req
                 [:uri]
                 #(if (= % "/")
                    "/index.html"
                    %)))))

;;
;; The Compojure application
;;
(def app
  "This is the Compojure application"
  (-> main-routes
      (wrap-session {:store (cookie-store)})
      wrap-params
      wrap-content-type
      wrap-dir-index
      cmpjr-hndlr/site))

(defn -main
  "The main entry point of the application"
  [& args]
  (println "-main: args" *command-line-args*)
  (let [port (or (first *command-line-args*) 8080)]
    (jetty/run-jetty app {:port port})))