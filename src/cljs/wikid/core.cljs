(ns wikid.core
  (:require
   [goog.dom :as dom]
   [reagent.core :as r ]
   [re-frame.core :as rf ]
   ;; [re-frisk-remote.core :as re-frisk-remote]
   [wikid.events :as events]
   [wikid.config :as config]
   [reagent.dom :as rd ]
   [wikid.views :as v]
   ))

(defn dev-setup []
  (when config/debug?
    ;; (re-frisk-remote/enable-re-frisk-remote! {:events? true :enable-re-frame-10x? true})
    (println "dev mode")))

(defn mount-root []
  (rd/render [v/wikid] (.getElementById js/document "app")))

(defn ^:after-load re-render []
  (mount-root))

(defn ^:export init []
  ;;(routes/app-routes)
  (rf/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (rf/clear-subscription-cache!)
  (mount-root))

(defonce init-app (init))
;;-----------------------------------------------
(comment
    "here I can experiment with repl"
    (defn whatever []
      (+ 1 1))
    (whatever)
    )

