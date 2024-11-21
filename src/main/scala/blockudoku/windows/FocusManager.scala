package blockudoku.windows

class FocusManager(var focusState: FocusState)

enum FocusState {
  case Grid
  case Elements
}