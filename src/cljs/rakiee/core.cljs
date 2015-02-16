(ns rakiee.core
    (:require [reagent.core :as reagent :refer [atom]]
              [reagent.session :as session]
              [secretary.core :as secretary :include-macros true]
              [goog.events :as events]
              [goog.history.EventType :as EventType]
              [cljsjs.react :as react]
              [rakiee.constants :as c])
    (:import goog.History))


;; TODO unit test for reagent
;; TODO figwhell for node-webkit

;; =================
;; Constants in rakiee.data-defintions

;; =================
;; Data definitions in rakiee.data-defintions

;; TODO define data definitions in clojurescript

;; World State is ... (give WS a better name)

(def app-state
  (reagent/atom
   {:tasks
    [{:todo c/TODO :headline "Remove Ace-dependency from enterTask.js" :key "orgNode_33.##" }
     {:todo c/DOING :headline "AuxMoney Test starten" :key "orgNode_34.##"}
     {:todo c/DOING :headline "Karo und Diana das Briefing für das Designn schicken" :key "orgNode_35.##"}
     {:todo c/DOING :headline "Licht reklamieren, Kontoauszug raussuchen" :key "orgNode_36.##"}
     {:todo c/DOING :headline "Bräter 4 Stunden toasten" :key "orgNode_37.##"}
     {:todo c/TODO :headline "Ich teile nicht! schreiben" :key "orgNode_38.##"}
     {:todo c/DONE :headline "Verzeichnis-rakiee von Grund auf aufbauen, ohne leinigen templates" :key "orgNode_39.##"}]}))

;; =================
;; Functions:

;;
(defn task [t]
  ;^{:key t}
  [:tr
   [:td (:todo t)]
   [:td (:headline t)]])

(defn task-list []
  [:table
   (for [t (:tasks @app-state)]
     [task t])])

(defn big-bang []
  (reagent/render-component
    [task-list]
    (.getElementById js/document "root")))

;(big-bang)



;; ;; -------------------------
;; ;; Views

;; (defn home-page []
;;   [:div [:h2 "Welcome to rakiee"]
;;    [:div [:a {:href "#/about"} "go to about page"]]])

;; (defn about-page []
;;   [:div [:h2 "About rakiee"]
;;    [:div [:a {:href "#/"} "go to the home page"]]])

;; (defn current-page []
;;   [:div [(session/get :current-page)]])

;; ;; -------------------------
;; ;; Routes
;; (secretary/set-config! :prefix "#")

;; (secretary/defroute "/" []
;;   (session/put! :current-page #'home-page))

;; (secretary/defroute "/about" []
;;   (session/put! :current-page #'about-page))

;; ;; -------------------------
;; ;; History
;; ;; must be called after routes have been defined
;; (defn hook-browser-navigation! []
;;   (doto (History.)
;;     (events/listen
;;      EventType/NAVIGATE
;;      (fn [event]
;;        (secretary/dispatch! (.-token event))))
;;     (.setEnabled true)))

;; -------------------------
;; Initialize app
(defn init! []
  (reagent/render-component [task-list] (.getElementById js/document "app")))
