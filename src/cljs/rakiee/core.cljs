(ns rakiee.core
    (:require [reagent.core :as reagent :refer [atom]]
              [reagent.session :as session]
              [secretary.core :as secretary :include-macros true]
              [goog.events :as events]
              [goog.history.EventType :as EventType]
              [cljsjs.react :as react])
    (:import goog.History))


;; TODO unit test for reagent
;; TODO figwhell for node-webkit

;; =================
;; Constants:

(def TODO "TODO")
(def DONE "DONE")
(def DOING "DOING")
(def ALL "ALL")

;; =================
;; Data definitions:

;; World State is ... (give WS a better name)

(def app-state
  (reagent/atom
   {:tasks
    [{:todo TODO :headline "Remove Ace-dependency from enterTask.js"}
     {:todo DOING :headline "AuxMoney Test starten"}
     {:todo DOING :headline "Karo und Diana das Briefing für das Designn schicken"}
     {:todo DOING :headline "Licht reklamieren, Kontoauszug raussuchen"}
     {:todo DOING :headline "Bräter 4 Stunden toasten"}
     {:todo TODO :headline "Ich teile nicht! schreiben"}
     {:todo DONE :headline "Verzeichnis-rakiee von Grund auf aufbauen, ohne leinigen templates"}]}))

;; =================
;; Functions:


(defn task [t]
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
