(ns wikid.events
  (:require
   [re-frame.core :as rf]
   [re-frame.db]
   [wikid.db :as db]))

(rf/reg-event-db ::initialize-db
 (fn [_ _]
   db/default-db))

(rf/reg-event-db :claim-form-changed
 (fn [db [_ val]] 
   (let [statements-count (count @(rf/subscribe [:claims]))]
     (update-in db [:claims] conj {(keyword (str (inc statements-count)))
                                   val}))
   )
 )

(rf/reg-event-db :add-claim
 (fn [db [_ val]] 
   (let [statements-count (count @(rf/subscribe [:claims]))]
     (update-in db [:claims] conj {(keyword (str (inc statements-count)))
                                       val}))
   )
 )

