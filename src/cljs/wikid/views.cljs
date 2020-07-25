(ns wikid.views
  (:require
   [reagent.core :as r]
   [re-frame.core :as rf]
   [wikid.subs :as subs]
   [wikid.events :as events]
   [shadow.remote.runtime.cljs.browser]))

(defn add-statement-from-input [event]
  (when (-> event .-key (= "Enter"))
    (rf/dispatch[:add-claim (-> event .-target .-value)])))

(defn wikid []
  (let [claims (rf/subscribe [:claims])] 
    [:div
     [:div "WikiD"]
     [:input {:placeholder  "state your mind"
              :value (rf/subscribe [:input-claim])
              :on-key-press add-statement-from-input}]

     (when @claims
       (into [:ul]
        (map (fn [claim]
               [:li
                (val claim)
                [:button
                 {:on-click #(rf/dispatch
                             [:add-claim (-> % .-target .-value)])}
                 "delete"]])
         @claims)))
     ]))


