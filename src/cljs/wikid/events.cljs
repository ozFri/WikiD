(ns wikid.events
  (:require
   [re-frame.core :as rf]
   [re-posh.core :as rp]
   [wikid.util :as util]
   [wikid.db :as db]))

(rp/reg-event-ds ::initialize-db
 (fn [_ _]
   db/default-db))

(rp/reg-event-ds :magic-bar-input
 (fn [db [_ val]] 
   ;;(util/validate-claim val)
   [[:db/add 1 :magic-bar/content val]]
   )
 )

(rp/reg-event-ds :delete-claim
 (fn [db [_ id]] 
   [[:db/retract id :wikid/type :type/claim]]
   )
 )

(rp/reg-event-ds :vote
 (fn [db [_ claim type user context]] 
   [
    [:db/add "new-vote" :wikid/type :type/vote]
    [:db/add "new-vote" :vote/voter user]
    [:db/add "new-vote" :vote/context context]
    [:db/add "new-vote" :vote/type type]
    [:db/add "new-vote" :vote/target claim]]
   )
 )

(rp/reg-event-ds :add-claim
 (fn [db [_ val]] 
   [[:db/add "new-claim" :wikid/type :type/claim]
    [:db/add "new-claim" :claim/title val]]
   )
 )

