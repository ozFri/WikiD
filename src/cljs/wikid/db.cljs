(ns wikid.db
  (:require
   [datascript.core :as d]
   [re-posh.core :as rp]))

(def default-db
  [{:db/id             -5
    :wikid/type        :type/magic-bar
    :magic-bar/content ""
    }
   {:db/id       -4
    :wikid/type  :type/claim
    :claim/title "Roses are red"
    }
   {:db/id       -3
    :wikid/type  :type/claim
    :claim/title "Violets are blue"
    }
   {:db/id       -2
    :wikid/type  :type/claim
    :claim/title "Socrates is mortal"
    }
   {:db/id       -1
    :wikid/type  :type/claim
    :claim/title "And so are you"
    }
   
   {:db/id       -6
    :wikid/type  :type/user
    :user/name "Oz"
    }
   {:db/id       -7
    :wikid/type  :type/context
    :context/name "General"
    }
   ])

(def conn (d/create-conn))
(rp/connect! conn)

(comment
  (wikid/call wikid/get-context []
   (fn [user]
     (d/transact! conn [(assoc user
                         :user/me true
                         :user/state :loaded)]))
   )
    )
(comment
  (datascript.core/db conn)
  (prn @conn)
  (.log js/console @conn)
    )
