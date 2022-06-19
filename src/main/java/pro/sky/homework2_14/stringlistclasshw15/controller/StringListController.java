package pro.sky.homework2_14.stringlistclasshw15.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.homework2_14.stringlistclasshw15.interfaces.StringList;

@RestController
public class StringListController {
    private final StringList stringList;

    public StringListController(StringList stringList) {
        this.stringList = stringList;
    }

    @GetMapping(path = "/getAll")
    public String[] getAllElements() {
        return stringList.getAll();
    }

    @GetMapping(path = "/add")
    public String addElement(@RequestParam("string") String str) {
        return stringList.add(str);
    }

    @GetMapping(path = "/addindex")
    public String addElementByIndex(@RequestParam("id") int index, @RequestParam("string") String str) {
        return stringList.add(index, str);
    }

    @GetMapping(path = "/set")
    public String setElement(@RequestParam("id") int index, @RequestParam("string") String str) {
        return stringList.set(index, str);
    }


    @GetMapping(path = "/removeid")
    public String removeElementById(@RequestParam("id") int index) {
        return stringList.remove(index);
    }

    @GetMapping(path = "/remove")
    public String removeElement(@RequestParam("string") String str) {
        return stringList.remove(str);
    }

    @GetMapping(path = "/contains")
    public boolean isContainsElement(@RequestParam("string") String str) {
        return stringList.contains(str);
    }

    @GetMapping(path = "/indexof")
    public int whatIndexOfElement(@RequestParam("string") String str) {
        return stringList.indexOf(str);
    }

    @GetMapping(path = "/lastindex")
    public int whatLastIndexOfElement(@RequestParam("string") String str) {
        return stringList.lastIndexOf(str);
    }

    @GetMapping(path = "/get")
    public String getElementById(@RequestParam("id") int index) {
        return stringList.get(index);
    }

    @GetMapping(path = "/equals")
    public boolean isListsEquals(@RequestParam("list") StringList otherList) {
        return stringList.equals(otherList);
    }

    @GetMapping(path = "/size")
    public int sizeOfList() {
        return stringList.size();
    }

    @GetMapping(path = "/empty")
    public boolean isEmptyOfList() {
        return stringList.isEmpty();
    }

    @GetMapping(path = "/clear")
    public void clearList() {
        stringList.clear();
    }

    @GetMapping(path = "/new")
    public String[] newArray() {
        return stringList.toArray();
    }

}
