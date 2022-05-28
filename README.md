## DocxFormattingChecker

### A tool for checking and fixing MS Word document formatting.

Needs a `.docx` document that you'd like to check and/or fix and a `config.json` with a set of formatting rules.

See examples of `config.json` in `src/test/resources`

### Run app

    ./gradlew run --args="/Path/To/Config.json /Path/To/Document.docx"

### Run tests

    ./gradlew test

---

## Supported options

- `styles<string: style>[]`
    - `style`
- `section`
    - orientation - string
    - margins - double
    - page height - double
    - page width - double
- `pages`
    - min - int
    - max - int
- `footer`
    - type - string(text/page)
    - alignment - string
    - error message -string
- `drawing`
    - textStartsWith - string
    - `paragraph`
    - `style`
- `requiredHeadings[]`
    - level - int
    - text -string
- `paragraphsCount`
    - min - int
    - max - int
- existence
    - paragraphs[] - `paragraph`
    - runs[] - `runs`
- filename - string(regexp)
- findHeadingsByTOC - bool
- generateNewDocument - bool

### Only inside other options

- `style`
    - paragraphs[] - int
    - paragraphProperties - `paragraph`

- `paragraph`
    - paragraphs[] - int
    - `runs[]`
    - alignment - string
    - firstLineIndent - double
    - leftIndent - double
    - rightIndent - double
    - lineSpacing - double
    - spacingBefore - double
    - spacingAfter - double
    - pageBreakBefore - bool
    - maxRunsCount - int
    - minRunsCount - int
    - text - string(regexp)

- `run`
    - fontFamily - string
    - fontSize - double
    - bold - bool
    - italic - bool
    - strikethrough - bool
    - underline - string
    - textColor - string
    - characterSpacing - double(positive or negative)
    - vertAlign - string(subscript/superscript)
    - text - string(regexp)