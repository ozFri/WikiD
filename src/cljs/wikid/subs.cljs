(ns wikid.subs
  (:require
   [re-posh.core :as rp]
   [re-frame.core :as rf]
            ))

(rp/reg-sub ::magic-bar
 (fn [_ _]
   {:type    :pull
    :pattern '[:db/id :magic-bar/content]
    :id 1
    }))

(rp/reg-sub ::input-claim
 (fn [_ _]
   {:type :query
    :query '[:find ?id
             :where [?id :app/type :type/input-claim-form]]}))

(rp/reg-sub ::claim-ids
 (fn [_ _]
   {:type  :query
    :query '[:find [?cid ...]
             :where [?cid :wikid/type :type/claim]]}))

(rp/reg-query-sub ::claim-votes
'[:find [?vid ...]  
   :in $ ?claim
  :where [?vid :vote/target ?cid] [(= ?claim ?cid)]]
 )

(rp/reg-sub ::claim
 (fn [_ [_ id]]
   {:type    :pull
    :pattern '[:db/id :claim/title]
    :id      id}))

(rp/reg-sub ::user
 (fn [_ [_ id]]
   {:type :query
    :query '[:find ?user
             :where [_ :user/name ?user]]
   :id id})
 )

(rp/reg-sub ::user-id
 (fn [_ _]
   {:type  :query
    :query '[:find ?id
             :where [?id :app/type :type/input-claim-form]]}))

(rp/reg-sub ::context
 (fn [_ [_ id]]
   {:type :query
    :query '[:find ?context
             :where [_ :context/name ?context]]
   :id id})
 )

(rp/reg-sub ::vote
 (fn [_ [_ id]]
   {:type :pull
   :pattern '[:db/id :vote/type]
   :id id})
 )

(rf/reg-sub :form-username)
(rf/reg-sub :form-password)
(rf/reg-sub :form-password-2)
