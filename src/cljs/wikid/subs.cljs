(ns wikid.subs
  (:require [re-frame.core :as rf]))


(rf/reg-sub :input-claim
 (fn [db]
   (:input-claim db)
   )
 )

(rf/reg-sub :claims
 (fn [db]
   (:claims db)
   )
 )
