# MultiViewTypeAdapter

**MultiViewTypeAdapter** provides an concise & intuitive syntax to configure multi-type adapters using DSL pattern.
> **MultiViewTypeAdapter** is based on [DiffUtil](https://developer.android.com/reference/androidx/recyclerview/widget/DiffUtil)

## Usage

### Step1: Defining Item Classes
define items that will be each type in RecyclerView by extends `BaseItem`
at this time, must specify id field of `BaseItem` with unique value.
```kotlin
sealed class ChatRoomItem(key: Any) : BaseItem(key) {

    data class MyWords(val key: String, val content: String) : ChatRoomItem(key)

    data class OpponentWords(val key: String, val content: String) : ChatRoomItem(key)

    data class DateSection(val date: String) : ChatRoomItem(date)
}
```

### Step2: Creating xml for item using DataBinding
create xml to be used in `MyWords` type
```xml
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

### Step3: Setting up an adapter with items and bindings
```kotlin
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

### Stpe4: Updating adapter using a list of multi type
```kotlin
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

## Installation

Add JitPack repository in your `build.gradle`:
```gradle
allprojects {
    repositories {
        google()
        jcenter()
    }
}
```

Specify **MultiViewTypeAdapter** in your `build.gradle` for app module:
```gradle
dependencies {
    implementation ""
}
```

To use MultiViewTypeAdapter, you must use databinding. Add following in your `build.gradle` for app module:
```gradle
android {

    ...

    dataBinding {
        enabled = true
    }
}
```

## License
`MultiViewTypeAdapter` is under MIT License. ([detail](LICENSE))
