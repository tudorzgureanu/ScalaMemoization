package com.ted.macros

import scala.annotation.StaticAnnotation
import scala.language.experimental.macros
import scala.reflect.macros.whitebox.Context

class memo extends StaticAnnotation {
  def macroTransform(annottees: Any*): Any = macro memoMacro.impl
}

private[macros] object memoMacro {
  def impl(c: Context)(annottees: c.Expr[Any]*): c.Expr[Any] = {
    import c.universe._
    val trees = annottees.map(_.tree).toList
    val resultTree = trees match {
      case DefDef(mods, name, tparams, vparams, returnTypeTree, body) :: _ =>
        val paramsValue = vparams.flatten.map(_.name)
        val paramsType = vparams.flatten.map(_.tpt)
        val cacheName = s"cache${name.toString}${paramsType.mkString}"
        val cacheTerm = TermName(cacheName)
        q"""
            val $cacheTerm = scala.collection.mutable.Map[Any, $returnTypeTree]()
            $mods def $name[..$tparams](...$vparams): $returnTypeTree = {
            $cacheTerm.getOrElseUpdate($paramsValue, $body)
          }"""
      case _ => c.error(c.enclosingPosition, "This annotation can be used only by methods.")
        trees.head
    }
    c.Expr[Any](resultTree)
  }
}
