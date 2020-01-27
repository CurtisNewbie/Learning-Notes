# Chapter 12 XML and JSON Processing

**_`XML part is skipped`_**

## JSON

JSON uses `"name": "value" pairs` to declare internal properties.

Json uses the following conventions to represent data in JSON:

- **Number**
- **String**
- **value**: string, number, true, false, null, object, or an array
- **array**: using '[' and ']'
- **object**: using '{' and '}'

## JSON-P

There are many libraries available to parse and generate JSON, In JavaEE, the standard API for processing JSON is known as `JSON-P`, where P stands for Processing.

    javax.json
    javax.json.spi
    javax.json.stream

JSON-P provides two way to process a JSON object:

- `Object Model API`: maintain a tree-like model that provides flexible navigation and queries
- `Stream API`: uses `pull-parsing model` that only extracts the requested data without maintaining the structure

## Object Model API

The object and array in JSON are represented by:

- javax.json.`JsonObject`: provides a **Map view**
- javax.json.`JsonArray`: provides a **List view**

### JSON Builder

To build a JSON for these two representation classes, we use **builders**:

- javax.json.`JsonObjectBuilder`
- javax.json.`JsonArrayBuilder`

e.g.,

    public JsonObject buildPurchaseOrder() {

    return Json.createObjectBuilder()
        .add("order", Json.createObjectBuilder() // create object "order"
            .add("id", "1234")
            .add("date", "05/06/2013")
            .add("customer", Json.createObjectBuilder() // create nested object "customer"
                .add("first_name", "James")
                .add("last_name", "Rorrison")
                .add("email", "j.rorri@me.com")
                .add("phoneNumber", "+44 1234 1234"))
            .add("content", Json.createObjectBuilder() // create nested object "content"
                    .add("order_line", Json.createArrayBuilder() // create nested array "order_line" which contains two object
                        .add(Json.createObjectBuilder()
                            .add("item", "H2G2")
                            .add("quantity", "1")
                            .add("unit_price", "23.5"))
                        .add(Json.createObjectBuilder()
                            .add("item", "Harry Potter")
                            .add("quantity", "2")
                            .add("unit_price", "34.99"))))
            .add("credit_card", Json.createObjectBuilder() // create nested object "credit_card"
                .add("number", "1357")
                .add("expiry_date", "10/13")
                .add("control_number", "234")
                .add("type", "Visa"))).build();
    }

### JSON Reader

Instead of building an JSON, we can **create one through processing the data form a data source** using **reader**. We get a reader using `Json`.

- javax.json.`JsonReader`

e.g.,

    JsonReader reader = Json.createReader(new FileReader("order.json"));
    JsonObject jsonObject = reader.readObject();
    jsonObject = jsonObject.getJsonObject("order");

**JsonObject** and **JsonArray** are both subtype of `JsonStructure`. JsonReader also provides general way to read the **JsonSructure** using `read()`, which returns either JsonObject or JsonArray. Depending on the value type, we can extract the value with corresponding methods. `jsonStructure.toString()` also returns a JSON representation of the object model.

    javax.json.JsonStructure

e.g.,

    // read() method
    JsonReader reader = Json.createReader(new FileReader("order.json"));
    JsonStructure structure = reader.read();

    // check value type
    if(structure.getValueType().equals(NUMBER)){
        // extract value
       JsonNumber jsonNum = (JsonObject) structure.getJsonNumber("num");
       double value = jsonNum.doubleValue();
    }

### JSON Writer

Similarly, we can use writer to write the **JsonObject** and **JsonArray** to an output source. We use the same way to create a writer using `Json`.

    javax.json.JsonWriter

e.g.,

    JsonWriter writer = Json.createWriter(new FileWriter("order.json"));
    writer.writeObject(jsonObject);
    writer.close();

## Streaming API - Parsing JSON

Streaming API uses `pull-parsing model`, which means that the **parser generates events when a name:value pair is reached or the start/end is reached**. This also means that the parsing is **forward and readonly**.

- javax.json.stream
- javax.json.stream.`JsonParser`

To use streamming api, we still use `Json` as a factory object.

    JsonParser parser = Json.createParser(new StringReader("{ }"));

Using streaming api to parse JSON is extremely quick but complicated, we need to handle `JsonParser.Event`:

    while(parser.hasNext()){
        // get the event
        Event e = parser.next();

        // check if it's the name of the key in a key:value pair
        if(e.equals(Event.KEY_NAME)){

            // check if it's the key for "age" property
            if(parser.getString().equals("age")){
                // jump to the value
                parser.next();
                // get the value
                System.out.println("Age:" + parser.getInt());
            }
        }
    }

## Streaming API - Generating JSON

To generate JSON with stream API, we use `JsonGenerator` to write values and objects. `JSON-B` that comes with JavaEE 8 may be a better idea.

    javax.json.stream.JsonGenerator

e.g., (exact copy from the book for demonstration only)

    StringWriter writer = new StringWriter();
        JsonGenerator generator = Json.createGenerator(writer);
        generator.writeStartObject()
            .write("id", "1234")
            .write("date", "05/06/2013")
            .writeStartObject("customer")
                .write("first_name", "James")
                .write("last_name", "Rorrison")
                .write("email", "j.rorri@me.com")
                .write("phoneNumber", "+44 1234 1234")
            .writeEnd()
            .writeStartArray("content")
                .writeStartObject()
                    .write("item", "H2G2")
                    .write("unit_price", "23.5")
                .writeStartObject()
                    .write("quantity", "1")
                .writeEnd()
                    .write("item", "Harry Potter")
                    .write("unit_price", "34.99")
                    .write("quantity", "2")
                .writeEnd()
            .writeEnd()
            .writeStartObject("credit_card")
                .write("number", "123412341234")
                .write("expiry_date", "10/13")
                .write("control_number", "234")
                .write("type", "Visa")
            .writeEnd()
            .writeEnd()
            .close();
        return writer;
    }
