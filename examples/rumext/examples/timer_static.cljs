(ns rumext.examples.timer-static
  (:require [rumext.core :as rum :refer-macros [defc]]
            [rumext.examples.util :as util]))

(defc timer-static
  {:mixins [rum/reactive]
   :derive-state (fn [state]
                   (prn "derive-state")
                   state)
   :did-mount (fn [state]
                (prn "did-mount")
                state)
   :did-update (fn [state snapshot]
                 (prn "did-update")
                 state)}
  [label ts]
  (prn "render")
  [:div label ": "
   [:span {:style {:color @util/*color}} (util/format-time ts)]])

(defn mount! [el]
  (rum/mount (timer-static "Static" @util/*clock) el)
  (add-watch util/*clock :timer-static
             (fn [_ _ _ new-val]
               (prn "****")
               (rum/mount (timer-static "Static" new-val) el))))
