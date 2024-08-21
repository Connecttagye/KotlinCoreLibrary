package ak.core.ui.styles

import androidx.compose.ui.graphics.Color

val listOfBackgroundColors = arrayOf(
    Color(0xFFf8130d),
    Color(0xFFb8070d),
    Color(0xFF7a000b),
    Color(0xFFff7900),
    Color(0xFFfff8b3),
    Color(0xFFfcf721),
    Color(0xFFf8df09),
    Color(0xFF8a3a00),
    Color(0xFFc0dc18),
    Color(0xFF88dd20),
    Color(0xFF07ddc3),
    Color(0xFF01a0a3),
    Color(0xFF59cbf0),
    Color(0xFF005FFF),
    Color(0xFF022b6d),
    Color(0xFFfa64e1),
    Color(0xFFfc50a6),
    Color(0xFFd7036a),
    Color(0xFFdb94fe),
    Color(0xFFb035f8),
    Color(0xFF7b2bec),
    Color(0xFFFFFFFF),
    Color(0xFFb0aaae),
    Color(0xFF768484),
    Color(0xFF333333),
    Color(0xFF0a0c0b)
)

val listOfPriorityColors = arrayOf(
    Color.Red,
    Color.Yellow,
    Color.Green,
    Color.Cyan,
    Color.Transparent,
)

val listOfTextColors = arrayOf(
    Color(0xFF000000),
    Color(0xFF141414),
    Color(0xFF1F1F1F),
    Color(0xFF313131),
    Color(0xFF3F3F3F),
    Color(0xFF525252),
    Color(0xFF696969),
    Color(0xFF818181),
    Color(0xFF979797),
    Color(0xFFA7A7A7),
    Color(0xFFBBBBBB),
    Color(0xFFD1D1D1),
    Color(0xFFE7E7E7),
    Color(0xFFFFFFFF),
)

object Colors {



    val getPriorityOfColor: (Color) -> String = {
        when (it) {
            Color.Red -> "URGENT"
            Color.Yellow -> "IMPORTANT"
            Color.Green -> "NORMAL"
            Color.Cyan -> "LOW"
            else -> {
                "NON"
            }
        }
    }

    //
    val getColorOfPriority: (String) -> Color = {
        when (it) {
            "URGENT" -> Color.Red
            "IMPORTANT" -> Color.Yellow
            "NORMAL" -> Color.Green
            "LOW" -> Color.Cyan
            else -> {
                Color.Transparent
            }
        }
    }
}