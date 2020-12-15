# MultiViewTypeAdapter

`MultiViewTypeAdapter` is a library that makes it easy to configure  multi type adapters with DSL pattern. and use `DiffUtils` internally

## Setup

add JitPack repository to your project's `build.gradle` file
```
allprojects {
    repositories {
        google()
        jcenter()
    }
}
```

add `MultiViewTypeAdapter` dependency to your app module's `build.gradle` file
```
dependencies {
    implementation ""
}
```

you must use databinding, add following code to your app module's `build.gradle` file
```
android {

    ...

    dataBinding {
        enabled = true
    }
}
```

## Usage
### Step1: Define Item Classes
define items that will be each type in RecyclerView by extends `BaseItem`
at this time, must specify id field of `BaseItem` with unique value.
```
sealed class ChatRoomItem(key: Any) : BaseItem(key) {

    data class MyWords(val key: String, val content: String) : ChatRoomItem(key)

    data class OpponentWords(val key: String, val content: String) : ChatRoomItem(key)

    data class DateSection(val date: String) : ChatRoomItem(date)
}
```
### Step2: Create xml file for item using DataBinding
create xml to be used in `MyWords` type
```
<layout>
    <data>
        <variable
            name="item"
            type="MyWords" />
    </data>

    ...
	
    <TextView
        ...
        android:text="@{item.content}"/>
</layout>
```
create another xml in a similar way as above (for `OpponentWords`, `DateSection`)

### Step3: Setup adapter with items and bindings
```
adapter = multiViewTypeAdapter {
    type<MyWords, ItemMyWordsBinding> {
        onCreate {
            // callback onCreateViewHolder
        }
        onBind {
            // callbank onBindViewHolder
        }
    }

    type<OpponentWords, ItemOpponentWordsBinding> {
        ...
    }

    type<DateSection, ItemDateSectionBinding> {
        ...
    }
}

recyclerView.adapter = adapter
```
### Stpe4: Update adapter with list of multi type
```
adapter.update(
    listOf(
        DateSection("2020.12.31"),
        OpponentWords("key1", "5"),
        MyWords("key2", "4"),
        OpponentWords("key3", "3"),
        MyWords("key4", "2"),
        OpponentWords("key5", "1"),
        DateSection("2021.01.01"),
        OpponentWords("key6", "Happy new year"),
        OpponentWords("key7", "\uD83C\uDF8A"),
        MyWords("key8", "Happy new year \uD83C\uDF89\uD83C\uDF89")
    )
)
```

## License
