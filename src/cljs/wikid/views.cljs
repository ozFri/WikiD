(ns wikid.views
  (:require
   [reagent.core :as r]
   [re-frame.core :as rf]
   [re-posh.core :as rp]
   [wikid.subs :as subs]
   [wikid.events :as events]
   [wikid.views.claims :refer [claims-list claims-input]]
   [shadow.remote.runtime.cljs.browser]))

(defn form-input [[placeholder event subscription]]
  [:input {:placeholder placeholder
           :on-change #(rf/dispatch [event (-> % .-target .-value)])
            :value @(rf/subscribe [subscription])}])

(defn signup []
  [:form
   [form-input ["username" :form-username-change :form-username]]
   [form-input ["password" :form-password-change :form-password]]
   [form-input ["confirm-password" :form-password-2-change :form-password-2]]
   [:button "submit" {:on-change #(rf/dispatch [:signup-submit])}]])

(defn login [])

(defn wikid []
  (fn []
    [:div
     [:div "WikiD"]
     [signup]
     [login]
     [claims-input]
     [claims-list]
     ]))

(comment
  {:votes {:1 {:user    :1
               :vote    :agree
               :claim   :1
               :context [:2 :3 :4]}}}
    )
