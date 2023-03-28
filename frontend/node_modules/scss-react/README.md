
# react()
A useful scss mixin to manage your media queries with charme :tophat:

### Table of content:
- [Options](#options)
- [Usage](#usage)

Install the package via npm:

``` bash
npm i scss-react
```

and include it using an **@import** statement:

``` scss
@import '~scss-react';

/// @import 'node_modules/scss-slamp/dist/index.scss';
/// [...]
```

# Options
First of all we set up the media queries and features **we'll use along all the application**.

The library comes with a list of [default queries and features](https://github.com/DidoMarchet/scss-utopia/blob/main/src/queries.scss):

``` scss
$defaults: (
  "small": (min-width: 320px), 
  "medium": (min-width: 750px),
  "large": (min-width: 1000px),
  "xlarge": (min-width: 1300px),
  "pointer": "(pointer: fine) and (hover: hover)",
  "touch": "(pointer: coarse) and (hover: none)"
);
```

Using `$queries` variable in your scss stylesheet you can **easily extend and override** the default values adopting **consistent naming convention**:

``` scss
$queries: (
  "tablet": "(min-width: 768px) and (max-width: 1024px)",
  "xlarge": (min-width: 1600px),
  "xlarge-retina": "(-webkit-min-device-pixel-ratio: 2) and (min-width: 1300px)"
  /// [ ...other rules ]
);
```

❗ Combined rules, such as `(min-width: 768px) and (max-width: 1024px)`, must be a a **quoted string** `"(min-width: 768px) and (max-width: 1024px)"`.
If they are not, only the right side of the operator will be considered `(max-width: 1024px)`

The resulting set of values will be the merge of `$defaults` and `$queries` variables:

``` scss
/*
  "small": (min-width: 320px), 
  "medium": (min-width: 750px),
  "large": (min-width: 1000px),
  "xlarge": (min-width: 1600px), // overrited 
  "pointer": "(pointer: fine) and (hover: hover)",
  "touch": "(pointer: coarse) and (hover: none)",
  "tablet": "(min-width: 768px) and (max-width: 1024px)", // added
  "large-retina": "(-webkit-min-device-pixel-ratio: 2)  and (min-width: 1300px)" // added
*/
```


# Usage

Once we have declared all the queries we need, we can deliver tailored style to each them using the [react mixin](https://github.com/DidoMarchet/scss-utopia/blob/main/src/react.scss):

``` scss
@include react('medium'){
  body{
    background: black;
  }
}
a{
  @include react('pointer'){
    &:hover{
      color: red;
    }
  }
}

/*
  Will generate 

  @media (min-width: 750px)
    body {
      background: black;
    }
  }
  @media (hover: hover)
    a:hover {
      color: red;
    }
  }
*/
```
