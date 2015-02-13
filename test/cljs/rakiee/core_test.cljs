(ns rakiee.core-test
  (:require [cemerick.cljs.test :refer-macros [is are deftest testing use-fixtures done]]
            [reagent.core :as reagent :refer [atom]]
            [rakiee.core :as rc]
            [rakiee.constants :as c]))


(def isClient (not (nil? (try (.-document js/window)
                              (catch js/Object e nil)))))

(def rflush reagent/flush)

(defn add-test-div [name]
  (let [doc     js/document
        body    (.-body js/document)
        div     (.createElement doc "div")]
    (.appendChild body div)
    div))

(defn with-mounted-component [comp f]
  (when isClient
    (let [div (add-test-div "_testreagent")]
      (let [comp (reagent/render-component comp div #(f comp div))]
        (reagent/unmount-component-at-node div)
        (reagent/flush)
        (.removeChild (.-body js/document) div)))))


(defn found-in [re div]
  (let [res (.-innerHTML div)]
    (if (re-find re res)
      true
      (do (println "Not found: " res)
          false))))


;; (deftest test-home
;;   (with-mounted-component (rc/home-page)
;;     (fn [c div]
;;       (is (found-in #"Welcome to" div)))))

(deftest test-task
  (let [t1 {:todo c/TODO  :headline "Task 1"}
        t2 {:todo c/DOING :headline "Task 2"}
        t3 {:todo c/DONE  :headline "Task 3"}]
    (do
      (is (= (rc/task t1) [:tr [:td "TODO"]  [:td "Task 1"]]))
      (is (= (rc/task t2) [:tr [:td "DOING"] [:td "Task 2"]]))
      (is (= (rc/task t3) [:tr [:td "DONE"]  [:td "Task 3"]])))))
